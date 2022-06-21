import java.time.LocalDate
import java.time.temporal.TemporalAmount

class AutoIncrement {
    companion object {
        var userNumAutoIncrement: Int = 0
        var productNumAutoIncrement: Int = 0

        fun getUserNum(): Int {
            return ++this.userNumAutoIncrement
        }

        fun getProductNum(): Int {
            return ++this.productNumAutoIncrement
        }
    }
}

// 인터넷 쇼핑몰에 대한 클래스들을 만들어보자
// 유저 정보
class User(var name: String, var id: String, var pw: String) {
    val joinDate: LocalDate = LocalDate.now()
    var isLogin: Boolean = true // 로그인 여부
    var isDeleted: Boolean = false // 삭제 여부
    var userNum: Int
    var shoppingCartList: MutableList<PerchaseItem> =
        mutableListOf<PerchaseItem>() // 회원 쇼핑카트 리스트

    init {
        this.userNum = AutoIncrement.getUserNum()
        this.name = name
        this.id = id
        this.pw = pw
    }

    fun login(id: String, pw: String): Unit { // 로그인
        if (isDeleted) { // 삭제여부 확인
            println("삭제된 회원입니다.")
            println()
            return
        } else {
            if (isLogin) {
                println("이미 로그인되었습니다.")
                println()
                return
            } else {
                if (this.id == id && this.pw == pw) {
                    println("login successed")
                    println()
                    this.isLogin = true
                } else {
                    println("login failed")
                    println()
                }
            }
        }
    }

    fun logout(): Unit {
        if (isDeleted) { // 삭제여부 확인
            println("삭제된 회원입니다.")
            println()
            return
        } else if (!isLogin) {
            println("로그아웃 상태입니다.")
            return
        } else {
            println("logout")
            println()
            this.isLogin = false
        }
    }

    fun print(): Unit { //회원 정보 표시
        if (isDeleted) { // 삭제여부 확인
            println("삭제된 회원입니다.")
            println()
            return
        } else {
            println("회원 번호 : ${this.userNum}")
            println("회원 명 : ${this.name}")
            println("아이디 : ${this.id}")
            println("비밀번호 : ${this.pw}")
            println("가입일자 : ${this.joinDate}")
            println("로그인 여부 : ${this.isLogin}")
            println("삭제 여부 : ${this.isDeleted}")
            println()
        }
    }

    fun deleteUser(): Unit {
        if (isDeleted) { // 삭제여부 확인
            println("삭제된 회원입니다.")
            println()
            return
        } else {
            this.isDeleted = true
            println("회원이 삭제되었습니다.")
            println()
        }
    }

    //-----------------

    fun addShoppingCart(product: Product, optionNum: Int, amount: Int): Unit { // 장바구니에 상품 담기
        if (isDeleted) { // 삭제여부 확인
            println("삭제된 회원입니다.")
            println()
            return
        } else if (!isLogin) {
            println("로그인 후에 가능한 기능입니다.")
            return
        } else {
            if (!product.isOptionNum(optionNum)) {
                println("해당 상품이 존재하지 않습니다.")
                return
            }
            this.shoppingCartList.add(PerchaseItem(product, optionNum, amount))
            println("상품이 장바구니에 담겼습니다.")
        }
    }

    fun deleteShopptinCartItem(product: Product, optionNum: Int): Unit {
        if (isDeleted) { // 삭제여부 확인
            println("삭제된 회원입니다.")
            println()
            return
        } else if (!isLogin) {
            println("로그인 후에 가능한 기능입니다.")
            return
        } else {
            if (!product.isOptionNum(optionNum)) {
                println("해당 상품이 존재하지 않습니다.")
                return
            }
            this.shoppingCartList.forEachIndexed { index, it -> // 쇼핑카트에 담긴 아이템의 idex를 검색해야 함
                if (it.product == product && it.optionNum == optionNum) {
                    this.shoppingCartList.removeAt(index)
                    println("해당 상품을 장바구니에서 삭제하였습니다.")
                    return
                }

                println("해당 상품이 장바구니에 존재하지 않습니다.")
            }
        }
    }

    fun printShoppingCart(): Unit {
        var priceSum:Int = 0
        if (isDeleted) { // 삭제여부 확인
            println("삭제된 회원입니다.")
            println()
            return
        } else if (!isLogin) {
            println("로그인 후에 가능한 기능입니다.")
            return
        } else {
            println("장바구니 현황")
            if (shoppingCartList.isEmpty()) {
                println("\t장바구니에 담긴 상품이 없습니다.")
                return
            }
            else {
                shoppingCartList.forEach {
                    priceSum += (it.product.defaultPrice + it.product.getOptionPrice(it.optionNum))*it.amount
                    println("\t제품 번호 : ${it.product.productNum} / 제품명 : ${it.product.name} / 구매 수량 : ${it.amount} / 상품 가격 : ${it.product.defaultPrice}")
                    println(it.product.getOptionInfo(it.optionNum))
                    println("--------------------------------------------------------------------------")
                }
            }
            println("총 가격 : ${priceSum}")
        }
    }

//    fun buyShoppingCart(): Unit {
//
//    }
//
//    fun buyItem(product: Product,optionNum:Int, amount:Int){
//
//    }
}

class PerchaseItem(
    var product: Product,
    var optionNum: Int,
    var amount: Int
) {
    init {
        this.product = product
        this.optionNum = optionNum
        this.amount = amount
    }
}

class Product(
// 상품
    var name: String,
    var quantity: Int = 10,
    var defaultPrice: Int,
    var productNum: Int = 0
) {

    var optionAutoincrementNum = 0
    var optionPriceList: MutableList<ProductOption> =
        mutableListOf<ProductOption>()//[옵션 번호, 옵션명, 옵션 가격]을 속성으로 가지고 있는 클래스를 리스트로 받음

    init {
        this.productNum = AutoIncrement.getProductNum()
        productList.productNumMap.put(this.productNum, this)

        this.name = name
        this.quantity = quantity
        this.defaultPrice = defaultPrice
    }

    fun addOption(optionName: String, optionPrice: Int): Unit {
//음수 확인 -> 아니지 음수일 수 있지 -> 할인되는 옵션일 수도 있으니까! -> 그러면 default랑 비교해서 설정하자
        if (this.defaultPrice + optionPrice < 0) {
            println("총 가격이 0보다 작을 수는 없습니다.")
            println()
            return
        } else {
            optionAutoincrementNum++
            this.optionPriceList.add(
                ProductOption(
                    optionAutoincrementNum,
                    optionName,
                    optionPrice
                )
            )
            println("옵션이 추가되었습니다.")
            println()
        }
        productList.productNumMap.put(this.productNum, this)
    }

    fun printOption(): Unit {
        println(this.name)
        this.optionPriceList.forEach {
            println("\t옵션번호 : " + it.optionNum + " / 옵션명 : " + it.name + " / 가격 : " + it.price)
            println("--------------------------------------------------------------------------")
        }
    }

    fun isOption(): Boolean {
        return !optionPriceList.isEmpty()
    }

    fun getOptionInfo(optionNum: Int): String {
        this.optionPriceList.forEach {
            if (it.optionNum == optionNum) return "\t옵션번호 : ${it.optionNum} / 옵션명 : ${it.name} / 가격 : ${it.price}"
        }

        return "해당 상품에서 위 옵션은 존재하지 않습니다."
    }

    fun getOptionPrice(optionNum: Int): Int {
        this.optionPriceList.forEach {
            if (it.optionNum == optionNum) return it.price
        }
        return 0
    }

    fun isOptionNum(optionNum: Int): Boolean {
        this.optionPriceList.forEach {
            if (it.optionNum == optionNum) return true
        }
        return false
    }
}

class ProductOption(var optionNum: Int, var name: String, var price: Int) {
    init {
        this.optionNum = optionNum
        this.name = name
        this.price = price
    }
}

class ProductList(var productNumMap: MutableMap<Int, Product> = mutableMapOf<Int, Product>()) {
    fun print(): Unit {
        productNumMap.forEach {
            it.value.printOption()
        }
    }
}

class Sale(var user:User, var perchaseItem:PerchaseItem){
    init {
        this.user = user
        this.perchaseItem = perchaseItem
    }
}

class SalesHistory() {
    var sales:MutableList<Sale> = mutableListOf<Sale>()
}

val salesHistory = SalesHistory()
val user1: User = User("Mike", "a", "b")
//user1.print()
//user1.logout()
//user1.print()
//user1.deleteUser()
user1.print()

var productList: ProductList = ProductList()
val product1: Product = Product("제품1", 10, 1000)
product1.addOption("옵션1", 500)
product1.addOption("옵션2", 1000)
product1.addOption("옵션3", 1500)
product1.printOption()

val product2: Product = Product("제품2", 10, 2000)
product2.addOption("옵션1", 300)
product2.addOption("옵션2", 600)
product2.addOption("옵션3", 900)

productList.print() // 상품 표출

println("==========================================================================")
user1.printShoppingCart()
user1.addShoppingCart(product1, 3, 5)
user1.addShoppingCart(product2, 2, 6)
user1.addShoppingCart(product2, 9, 6)
user1.printShoppingCart()

user1.deleteShopptinCartItem(product1, 3)
user1.printShoppingCart()