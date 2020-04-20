package dev.fernandoflorez.platziconf.model

import java.io.Serializable
import java.util.Date

class Conference: Serializable {
    lateinit var speaker: String
    lateinit var title: String
    lateinit var description: String
    lateinit var tag: String
    lateinit var dateTime: Date
}