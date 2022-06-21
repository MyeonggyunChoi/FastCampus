class FootballPlayer(uniform: String, ball: String) {
    val uniform: String
    val ball: String

    init {
        this.uniform = uniform
        this.ball = ball
    }

    fun kick(){
        println("공을 찼다")
    }

    fun pass(){
        println("패스!")
    }
}

val footballPlayer = FootballPlayer("red","soccerball")
footballPlayer.kick()
footballPlayer.pass()