package org.testkotlin2.libB

import androidx.compose.runtime.collection.MutableVector
import org.testkotlin2.libA.*

class LibBClass {
    val la = LibAClass()
    val mv: MutableVector<String> = MutableVector<String>()
}

class LibBClass2 {
    val la = LibAClass2()
    val mv: MutableVector<String> = MutableVector<String>()
}