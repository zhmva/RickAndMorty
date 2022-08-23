package com.srndpty.rickmorty.data.local.characters.module

import com.srndpty.rickmorty.data.local.AppDataBase
import com.srndpty.rickmorty.data.local.characters.dao.CharactersDao
import com.srndpty.rickmorty.data.local.characters.repo.CharacterLocalRepositoryImpl
import com.srndpty.rickmorty.domain.repo.CharacterLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterLocalModule {
    @Provides
    @Singleton
    fun provideCharacterDao(appDataBase: AppDataBase): CharactersDao = appDataBase.getCharacterDao()

    @Provides
    fun provideCharacterLocalRepo(charactersDao: CharactersDao) : CharacterLocalRepository =
        CharacterLocalRepositoryImpl(charactersDao)
}