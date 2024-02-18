package project.phonestore

data class apiResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)