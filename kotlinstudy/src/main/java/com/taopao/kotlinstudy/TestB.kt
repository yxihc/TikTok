package com.taopao.kotlinstudy

class TestB(a: Int, b: Int) : test(a, b), IFace {


    constructor(c: String) : this(1, 2) {

    }

    override fun find() {
        super.find()

    }


    override fun bizi() {
        super.bizi()
    }

}