package dev.traydr.vu.domain.service

import dev.traydr.vu.domain.GlobalPair
import dev.traydr.vu.domain.repository.GlobalPairs
import dev.traydr.vu.domain.repository.GlobalPairsRepository

class GlobalPairsService(private val globalPairsRepository: GlobalPairsRepository) {

    fun get(key: String): GlobalPair? {
        return globalPairsRepository.findByKey(key)
    }

    fun create(key: String, value: String): Long {
        return globalPairsRepository.create(key, value)
    }

    fun update(key: String, value: String) {
        globalPairsRepository.update(key, value)
    }
}