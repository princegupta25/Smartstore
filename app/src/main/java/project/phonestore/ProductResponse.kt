package project.phonestore


data class ProductResponse(
    val limit: Int,
    val products: MutableList<Product>,
    val skip: Int,
    val total: Int
)