package com.example.onem2m_inae_mvp.data.local

import com.example.onem2m_inae_mvp.data.room.INAEDao

class INAELocalDataSource(
    private val inAEDao: INAEDao
): LocalDataSource {
}