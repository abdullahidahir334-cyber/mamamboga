package com.example.mamambogaapp.data

import com.example.mamambogaapp.R
import com.example.mamambogaapp.model.Category
import com.example.mamambogaapp.model.ProduceItem
import com.example.mamambogaapp.model.SocialAccount

object ProduceRepository {

    val items: List<ProduceItem> = listOf(
        // VEGETABLES
        ProduceItem(
            id = "veg_1",
            name = "Fresh Tomatoes",
            swahiliName = "Nyanya",
            category = Category.VEGETABLES,
            priceKsh = 80.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_tomato,
            description = "Ripe, firm red tomatoes freshly harvested from Kinangop farms.",
            isPopular = true
        ),
        ProduceItem(
            id = "veg_2",
            name = "Sukuma Wiki",
            swahiliName = "Kale",
            category = Category.VEGETABLES,
            priceKsh = 30.0,
            unit = "1 bunch",
            iconResId = R.drawable.ic_kale,
            description = "Crisp and fresh green sukuma wiki (kale) leaves.",
            isPopular = true
        ),
        ProduceItem(
            id = "veg_3",
            name = "Fresh Spinach",
            swahiliName = "Spinachi",
            category = Category.VEGETABLES,
            priceKsh = 30.0,
            unit = "1 bunch",
            iconResId = R.drawable.ic_spinach,
            description = "Nutritious dark green spinach leaves, washed and ready.",
            isPopular = true
        ),
        ProduceItem(
            id = "veg_4",
            name = "Red Onions",
            swahiliName = "Kitunguu Maji",
            category = Category.VEGETABLES,
            priceKsh = 120.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_onion,
            description = "Dry red onions with rich flavor for all your stews.",
            isPopular = true
        ),
        ProduceItem(
            id = "veg_5",
            name = "Potatoes",
            swahiliName = "Waru",
            category = Category.VEGETABLES,
            priceKsh = 150.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_potato,
            description = "Clean, firm Irish potatoes perfect for boiling, frying or mashing."
        ),
        ProduceItem(
            id = "veg_6",
            name = "Fresh Carrots",
            swahiliName = "Karoti",
            category = Category.VEGETABLES,
            priceKsh = 70.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_carrot,
            description = "Sweet, crunchy orange carrots."
        ),
        ProduceItem(
            id = "veg_7",
            name = "Green Cabbage",
            swahiliName = "Kabichi",
            category = Category.VEGETABLES,
            priceKsh = 60.0,
            unit = "1 head",
            iconResId = R.drawable.ic_cabbage,
            description = "Large, solid green cabbage head."
        ),
        ProduceItem(
            id = "veg_8",
            name = "Green Capsicum",
            swahiliName = "Hoho",
            category = Category.VEGETABLES,
            priceKsh = 100.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_capsicum,
            description = "Aromatic green capsicum (hoho) for sweet stew aroma."
        ),
        ProduceItem(
            id = "veg_9",
            name = "Garlic Clove Pack",
            swahiliName = "Kitunguu Saumu",
            category = Category.VEGETABLES,
            priceKsh = 50.0,
            unit = "1 pack",
            iconResId = R.drawable.ic_garlic,
            description = "Aromatic garlic cloves pack for cooking."
        ),

        // FRUITS
        ProduceItem(
            id = "fruit_1",
            name = "Sweet Bananas",
            swahiliName = "Ndizi",
            category = Category.FRUITS,
            priceKsh = 100.0,
            unit = "1 bunch",
            iconResId = R.drawable.ic_banana,
            description = "Naturally ripened, sweet dessert bananas.",
            isPopular = true
        ),
        ProduceItem(
            id = "fruit_2",
            name = "Ripe Mangoes",
            swahiliName = "Mwembe",
            category = Category.FRUITS,
            priceKsh = 120.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_mango,
            description = "Juicy, sweet Apple mangoes from Ukambani.",
            isPopular = true
        ),
        ProduceItem(
            id = "fruit_3",
            name = "Hass Avocados",
            swahiliName = "Ovacado",
            category = Category.FRUITS,
            priceKsh = 40.0,
            unit = "1 piece",
            iconResId = R.drawable.ic_avocado,
            description = "Creamy, perfectly ripe Hass avocados.",
            isPopular = true
        ),
        ProduceItem(
            id = "fruit_4",
            name = "Sweet Watermelon",
            swahiliName = "Tikiti Maji",
            category = Category.FRUITS,
            priceKsh = 200.0,
            unit = "1 whole fruit",
            iconResId = R.drawable.ic_watermelon,
            description = "Heavy, sweet red watermelon slice / full fruit."
        ),
        ProduceItem(
            id = "fruit_5",
            name = "Fresh Oranges",
            swahiliName = "Chungwa",
            category = Category.FRUITS,
            priceKsh = 150.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_orange,
            description = "Juicy citrus oranges rich in Vitamin C."
        ),

        // MAMA GOODS
        ProduceItem(
            id = "good_1",
            name = "Fresh Farm Eggs",
            swahiliName = "Mayai ya Kienyeji",
            category = Category.MAMA_GOODS,
            priceKsh = 300.0,
            unit = "1 tray (30 eggs)",
            iconResId = R.drawable.ic_egg,
            description = "Clean, fresh farm eggs packed in tray.",
            isPopular = true
        ),
        ProduceItem(
            id = "good_2",
            name = "Refined Cooking Oil",
            swahiliName = "Mafuta ya Kupika",
            category = Category.MAMA_GOODS,
            priceKsh = 180.0,
            unit = "1 litre",
            iconResId = R.drawable.ic_oil,
            description = "Pure vegetable cooking oil for home frying."
        ),
        ProduceItem(
            id = "good_3",
            name = "Maize Flour (Sembe)",
            swahiliName = "Unga wa Ugali",
            category = Category.MAMA_GOODS,
            priceKsh = 150.0,
            unit = "2 kg packet",
            iconResId = R.drawable.ic_flour,
            description = "Grade 1 white maize meal flour for soft ugali.",
            isPopular = true
        ),
        ProduceItem(
            id = "good_4",
            name = "Pishori Aromatic Rice",
            swahiliName = "Mchele wa Pishori",
            category = Category.MAMA_GOODS,
            priceKsh = 220.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_rice,
            description = "Fragrant Mwea Pishori grade 1 rice."
        ),
        ProduceItem(
            id = "good_5",
            name = "Yellow Beans",
            swahiliName = "Maharagwe ya Njano",
            category = Category.MAMA_GOODS,
            priceKsh = 160.0,
            unit = "1 kg",
            iconResId = R.drawable.ic_beans,
            description = "Clean, easy-to-cook yellow beans."
        )
    )

    val socialAccounts: List<SocialAccount> = listOf(
        SocialAccount(
            title = "WhatsApp Chat",
            handleOrNumber = "+254 712 345 678",
            description = "Chat directly with Mama Mboga for custom orders & live updates.",
            iconRes = R.drawable.ic_whatsapp,
            colorRes = R.color.whatsapp_color,
            actionUrl = "https://wa.me/254712345678?text=Hello%20Mama%20Mboga!%20I%20would%20like%20to%20place%20an%20order."
        ),
        SocialAccount(
            title = "Facebook Page",
            handleOrNumber = "@MamaMbogaFreshKE",
            description = "Follow us on Facebook for daily market prices and farm stories.",
            iconRes = R.drawable.ic_facebook,
            colorRes = R.color.facebook_color,
            actionUrl = "https://facebook.com/MamaMbogaFreshKE"
        ),
        SocialAccount(
            title = "Instagram Profile",
            handleOrNumber = "@mamamboga_fresh",
            description = "See photos of our fresh daily farm harvests and recipes.",
            iconRes = R.drawable.ic_instagram,
            colorRes = R.color.instagram_color,
            actionUrl = "https://instagram.com/mamamboga_fresh"
        ),
        SocialAccount(
            title = "Twitter / X",
            handleOrNumber = "@MamaMbogaKE",
            description = "Get instant delivery announcements and tips.",
            iconRes = R.drawable.ic_twitter,
            colorRes = R.color.twitter_color,
            actionUrl = "https://twitter.com/MamaMbogaKE"
        ),
        SocialAccount(
            title = "Call Customer Care",
            handleOrNumber = "+254 712 345 678",
            description = "Call us directly for urgent delivery support or house directions.",
            iconRes = R.drawable.ic_phone,
            colorRes = R.color.phone_color,
            actionUrl = "tel:+254712345678"
        )
    )
}
