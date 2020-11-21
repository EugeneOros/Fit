package pl.edu.agh.oros.fit.modules

data class Person(
        val name: String = "",
        val level: SkillLevel = SkillLevel.BEGINNER,
        val active: Boolean = true
)




//{
//    var name: String? = null
//    var level: String? = null
//    var switch: Boolean = true
//
//    constructor(){}
//
//    constructor(name: String?, level: String?){
//        this.name = name
//        this.level = level
//    }
//}