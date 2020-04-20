package dev.fernandoflorez.platziconf.model

import java.io.Serializable

class Speaker: Serializable {
    var name: String = ""
    var jobtitle: String = ""
    var workPlace: String = ""
    var biography: String = ""
    var twitter: String = ""
    var imageUrl: String = ""
    var category: Int = 0
}