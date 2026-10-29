package com.example.mamambogaapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mamambogaapp.adapter.CartAdapter
import com.example.mamambogaapp.adapter.ProduceAdapter
import com.example.mamambogaapp.adapter.SocialAdapter
import com.example.mamambogaapp.data.ProduceRepository
import com.example.mamambogaapp.databinding.ActivityMainBinding
import com.example.mamambogaapp.databinding.DialogOrderSuccessBinding
import com.example.mamambogaapp.model.CartItem
import com.example.mamambogaapp.model.Category
import com.example.mamambogaapp.model.ProduceItem
import com.example.mamambogaapp.model.SocialAccount
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val allProduce = ProduceRepository.items
    private var filteredProduceList = allProduce.toList()
    private val cartMap = mutableMapOf<String, CartItem>()

    private lateinit var produceAdapter: ProduceAdapter
    private lateinit var cartAdapter: CartAdapter
    private lateinit var socialAdapter: SocialAdapter

    private var selectedCategory: Category = Category.ALL
    private var searchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        setupRecyclerViews()
        setupSearchAndCategoryFilters()
        setupBottomNavigation()
        setupPlaceOrderAction()
        updateCartUI()
    }

    private fun setupRecyclerViews() {
        // Produce Grid
        produceAdapter = ProduceAdapter { produceItem ->
            addToCart(produceItem)
        }
        binding.rvProduceGrid.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = produceAdapter
        }
        produceAdapter.submitList(filteredProduceList)

        // Cart List
        cartAdapter = CartAdapter(
            onQuantityPlus = { cartItem -> updateCartQuantity(cartItem, 1) },
            onQuantityMinus = { cartItem -> updateCartQuantity(cartItem, -1) },
            onItemRemove = { cartItem -> removeFromCart(cartItem) }
        )
        binding.rvCartItems.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cartAdapter
        }

        // Social List
        socialAdapter = SocialAdapter(ProduceRepository.socialAccounts) { account ->
            handleSocialAccountClick(account)
        }
        binding.rvSocialList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = socialAdapter
        }
    }

    private fun setupSearchAndCategoryFilters() {
        // Search Listener
        binding.etSearchProduce.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchQuery = s?.toString()?.trim().orEmpty()
                applyFilters()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Category Chips
        binding.chipGroupCategory.setOnCheckedStateChangeListener { _, checkedIds ->
            selectedCategory = when {
                checkedIds.contains(R.id.chipVegetables) -> Category.VEGETABLES
                checkedIds.contains(R.id.chipFruits) -> Category.FRUITS
                checkedIds.contains(R.id.chipMamaGoods) -> Category.MAMA_GOODS
                else -> Category.ALL
            }
            applyFilters()
        }

        // Header Cart Click
        binding.btnHeaderCart.setOnClickListener {
            binding.bottomNav.selectedItemId = R.id.nav_cart
        }
    }

    private fun applyFilters() {
        filteredProduceList = allProduce.filter { item ->
            val matchesCategory = when (selectedCategory) {
                Category.ALL -> true
                else -> item.category == selectedCategory
            }
            val matchesSearch = searchQuery.isEmpty() ||
                    item.name.contains(searchQuery, ignoreCase = true) ||
                    item.swahiliName.contains(searchQuery, ignoreCase = true)

            matchesCategory && matchesSearch
        }
        produceAdapter.submitList(filteredProduceList)
    }

    private fun addToCart(item: ProduceItem) {
        val existing = cartMap[item.id]
        if (existing != null) {
            existing.quantity += 1
        } else {
            cartMap[item.id] = CartItem(item, 1)
        }
        Toast.makeText(this, "Added ${item.name} to Cart!", Toast.LENGTH_SHORT).show()
        updateCartUI()
    }

    private fun updateCartQuantity(cartItem: CartItem, delta: Int) {
        val newQty = cartItem.quantity + delta
        if (newQty <= 0) {
            cartMap.remove(cartItem.item.id)
        } else {
            cartItem.quantity = newQty
        }
        updateCartUI()
    }

    private fun removeFromCart(cartItem: CartItem) {
        cartMap.remove(cartItem.item.id)
        updateCartUI()
    }

    private fun updateCartUI() {
        val itemList = cartMap.values.toList()
        cartAdapter.submitList(itemList)

        val totalItemsCount = itemList.sumOf { it.quantity }
        val subtotal = itemList.sumOf { it.totalKsh }
        val deliveryFee = if (subtotal > 0 && subtotal < 1000) 50.0 else 0.0
        val totalAmount = subtotal + deliveryFee

        // Header Badge
        if (totalItemsCount > 0) {
            binding.tvHeaderCartBadge.text = totalItemsCount.toString()
            binding.tvHeaderCartBadge.visibility = View.VISIBLE
        } else {
            binding.tvHeaderCartBadge.visibility = View.GONE
        }

        // Bottom Nav Badge
        val badge = binding.bottomNav.getOrCreateBadge(R.id.nav_cart)
        if (totalItemsCount > 0) {
            badge.isVisible = true
            badge.number = totalItemsCount
            badge.backgroundColor = getColor(R.color.amber_secondary)
        } else {
            badge.isVisible = false
        }

        // Empty Cart Visibility
        if (itemList.isEmpty()) {
            binding.layoutEmptyCart.visibility = View.VISIBLE
            binding.rvCartItems.visibility = View.GONE
            binding.cardDeliveryDetails.visibility = View.GONE
            binding.cardOrderSummary.visibility = View.GONE
        } else {
            binding.layoutEmptyCart.visibility = View.GONE
            binding.rvCartItems.visibility = View.VISIBLE
            binding.cardDeliveryDetails.visibility = View.VISIBLE
            binding.cardOrderSummary.visibility = View.VISIBLE
        }

        // Summary Breakdown Texts
        binding.tvSummarySubtotalKsh.text = "KSh ${subtotal.toInt()}"
        binding.tvSummaryDeliveryFeeKsh.text = if (deliveryFee == 0.0) "FREE" else "KSh ${deliveryFee.toInt()}"
        binding.tvSummaryTotalKsh.text = "KSh ${totalAmount.toInt()}"
        binding.btnPlaceOrder.text = "Place Order • KSh ${totalAmount.toInt()}"
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_products -> {
                    binding.viewProducts.visibility = View.VISIBLE
                    binding.viewCart.visibility = View.GONE
                    binding.viewSocial.visibility = View.GONE
                    true
                }
                R.id.nav_cart -> {
                    binding.viewProducts.visibility = View.GONE
                    binding.viewCart.visibility = View.VISIBLE
                    binding.viewSocial.visibility = View.GONE
                    true
                }
                R.id.nav_social -> {
                    binding.viewProducts.visibility = View.GONE
                    binding.viewCart.visibility = View.GONE
                    binding.viewSocial.visibility = View.VISIBLE
                    true
                }
                else -> false
            }
        }
    }

    private fun setupPlaceOrderAction() {
        binding.btnPlaceOrder.setOnClickListener {
            val location = binding.etDeliveryLocation.text.toString().trim()
            val houseNo = binding.etHouseNumber.text.toString().trim()

            if (location.isEmpty()) {
                binding.etDeliveryLocation.error = "Please enter delivery location"
                return@setOnClickListener
            }

            if (houseNo.isEmpty()) {
                binding.etHouseNumber.error = "Please enter house / apartment number"
                return@setOnClickListener
            }

            showOrderConfirmationDialog(location, houseNo)
        }
    }

    private fun showOrderConfirmationDialog(location: String, houseNo: String) {
        val subtotal = cartMap.values.sumOf { it.totalKsh }
        val deliveryFee = if (subtotal > 0 && subtotal < 1000) 50.0 else 0.0
        val total = subtotal + deliveryFee

        val orderNumber = "#MMB-${Random.nextInt(10000, 99999)}"
        val dialog = BottomSheetDialog(this)
        val dialogBinding = DialogOrderSuccessBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.tvOrderNumber.text = "Order ID: $orderNumber"
        dialogBinding.tvDialogDeliveryAddress.text = "Deliver to: $location, $houseNo"
        dialogBinding.tvDialogTotalPaid.text = "Total Amount: KSh ${total.toInt()}"

        dialogBinding.btnTrackWhatsApp.setOnClickListener {
            val message = "Hello Mama Mboga! My Order $orderNumber (KSh ${total.toInt()}) has been placed.\nDeliver to: $location, $houseNo."
            val url = "https://wa.me/254712345678?text=${Uri.encode(message)}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        dialogBinding.btnCloseDialog.setOnClickListener {
            cartMap.clear()
            updateCartUI()
            dialog.dismiss()
            binding.bottomNav.selectedItemId = R.id.nav_products
        }

        dialog.show()
    }

    private fun handleSocialAccountClick(account: SocialAccount) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(account.actionUrl))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Could not open ${account.title}", Toast.LENGTH_SHORT).show()
        }
    }
}
