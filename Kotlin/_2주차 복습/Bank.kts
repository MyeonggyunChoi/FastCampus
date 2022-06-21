import kotlin.math.absoluteValue

// 은행과 사람을 클래스로 만들어보자
// 기본적으로 은행이 갖고 있는 것 -> 계좌번호, 소유주
// 해당 은행으로 상속한다면? 아니다 인터페이스 써보자
// 이걸로 여러가지 은행을 만드는 거지
//open class Bank(var accontNumber: String, var holder: String) {
//    init {
//        this.accontNumber = accontNumber
//        this.holder = holder
//    }
//}

interface Bank {
    var haveMoney: Int // 갖고있는 자산

    fun save(amount: Int)  // 저금
    fun use(amount: Int)  // 출금
    fun post(getdata: Person, amount: Int)  // 송금
}

class ShinhanBank(var name: String, haveMoney: Int = 0) :
    Bank {
    override var haveMoney: Int = haveMoney
        set(value) {
            if (field > value) println("[${this.name}] 금액이 ${field - value}만큼 빠져나갔습니다. 현재 잔액 : ${value}")
            else if (field < value) println("[${this.name}] 금액이 ${(field - value).absoluteValue}만큼 들어왔습니다. 현재 잔액 : ${value}")
            field = value
        }

    init {
        this.name = name
    }

    override fun save(amount: Int) {
        this.haveMoney += amount
    }

    override fun use(amount: Int) {
        if (amount < this.haveMoney) this.haveMoney -= amount
        else println("현재 잔액은 ${this.haveMoney}입니다. 잔액초과입니다.")
    }

    override fun post(getdata: Person, amount: Int) {
        if (amount < getdata.bank.haveMoney) {
            getdata.bank.save(amount)
            this.use(amount)
        } else println("현재 잔액은 ${this.haveMoney}입니다. 잔액초과입니다.")
    }
}

class NonghyeobBank(var name: String, haveMoney: Int = 0) :
    Bank {
    override var haveMoney: Int = haveMoney
        set(value) {
            if (field > value) println("[${this.name}] 금액이 ${field - value}만큼 빠져나갔습니다. 현재 잔액 : ${value}")
            else if (field < value) println("[${this.name}] 금액이 ${(field - value).absoluteValue}만큼 들어왔습니다. 현재 잔액 : ${value}")
            field = value
        }

    init {
        this.name = name
    }

    override fun save(amount: Int) {
        this.haveMoney += amount
    }

    override fun use(amount: Int) {
        if (amount < this.haveMoney) this.haveMoney -= amount
        else println("현재 잔액은 ${this.haveMoney}입니다. 잔액초과입니다.")
    }

    override fun post(getdata: Person, amount: Int) {
        if (amount < getdata.bank.haveMoney) {
            getdata.bank.save(amount)
            this.use(amount)
        } else println("현재 잔액은 ${this.haveMoney}입니다. 잔액초과입니다.")
    }
}

class Person(var name: String, var bank: Bank, var accountNumber: Int) {
    init {
        this.name = name
        this.bank = bank
        this.accountNumber = accountNumber
    }
}

val shinhanBank: ShinhanBank = ShinhanBank("신한은행", 10000)
val user1: Person = Person("Mike", shinhanBank, 123456789)
val nonghyeobBank: NonghyeobBank = NonghyeobBank("농협은행", 10000)
val user2: Person = Person("Amy", nonghyeobBank, 987654321)
user1.bank.save(1000)
user1.bank.post(user2, 3000)