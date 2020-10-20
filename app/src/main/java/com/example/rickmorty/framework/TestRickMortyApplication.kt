package com.example.rickmorty.framework

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

class TestRickMortyApplication : RickMorty() {
    override fun modules(): List<Module> {
        return emptyList()
    }

    internal fun injectModule(module: Module) = loadKoinModules(module)
}