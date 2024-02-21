package be.marche.apptravaux.repository

import be.marche.apptravaux.database.AvaloirDao
import be.marche.apptravaux.entities.Avaloir
import be.marche.apptravaux.entities.Commentaire
import be.marche.apptravaux.entities.DateNettoyage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AvaloirRepository @Inject constructor(
    private val avaloirDao: AvaloirDao
) {

    fun getAll(): List<Avaloir> {
        return avaloirDao.getAll()
    }

    fun getAllAvaloirsNotDraftsList(): List<Avaloir> {
        return avaloirDao.getAllAvaloirsNotDraftsList()
    }

    fun getAllAvaloirsDraftsList(): List<Avaloir> {
        return avaloirDao.getAllAvaloirsDraftsList()
    }

    fun getAllDatesNettoyagesDraftsList(): List<DateNettoyage> {
        return avaloirDao.getAllDatesNettoyagesDraftsList()
    }

    fun getAllCommentairessDraftsList(): List<Commentaire> {
        return avaloirDao.getAllCommentairesDraftsList()
    }

    fun findAvaloirByIdFlow(avaloirId: Int): Flow<Avaloir> {
        return avaloirDao.getByIdFlow(avaloirId)
    }

    fun findDatesByIdFlow(avaloirId: Int): Flow<List<DateNettoyage>> {
        return avaloirDao.getDatesByAvaloirIdFlow(avaloirId)
    }

    fun findCommentairesByIdFlow(avaloirId: Int): Flow<List<Commentaire>> {
        return avaloirDao.getCommentairesByAvaloirIdFlow(avaloirId)
    }

    suspend fun insertAvaloirs(avaloirs: List<Avaloir>) {
        avaloirDao.insertAvaloirs(avaloirs)
    }

    suspend fun insertDates(dates: List<DateNettoyage>) {
        avaloirDao.insertDates(dates)
    }

    suspend fun insertCommentaires(commentaires: List<Commentaire>) {
        avaloirDao.insertCommentaires(commentaires)
    }

    fun insertAvaloirsNotSuspend(avaloirs: List<Avaloir>) {
        avaloirDao.insertAvaloirsNotSuspend(avaloirs)
    }

    fun insertDatesNotSuspend(dates: List<DateNettoyage>) {
        avaloirDao.insertDatesNotSuspend(dates)
    }

    fun insertCommentairesNotSuspend(commentaires: List<Commentaire>) {
        avaloirDao.insertCommentairesNotSuspend(commentaires)
    }

    suspend fun insertCommentaireDb(commentaire: Commentaire) {
        avaloirDao.insertCommentaire(commentaire)
    }

    suspend fun insertDateNettoyageDb(dateNettoyage: DateNettoyage) {
        avaloirDao.insertDateNettoyage(dateNettoyage)
    }

    suspend fun deleteAvaloirDraft(avaloirDraft: Avaloir) {
        avaloirDao.deleteAvaloirDraft(avaloirDraft)
    }

    fun deleteAvaloirDraftNotSuspend(avaloirDraft: Avaloir) {
        avaloirDao.deleteAvaloirDraftNotSuspend(avaloirDraft)
    }

    fun deleteDateNettoyageNotSuspend(dateNettoyage: DateNettoyage) {
        avaloirDao.deleteDateNettoyageNotSuspend(dateNettoyage)
    }

    fun deleteCommentaireNotSuspend(commentaire: Commentaire) {
        avaloirDao.deleteCommentaireNotSuspend(commentaire)
    }

    fun countProduits(): Int {
        return avaloirDao.countAvaloirs()
    }

    fun countCommentaire(): Int {
        return avaloirDao.countCommentaires()
    }

    fun countDateNettoyage(): Int {
        return avaloirDao.countDatesNettoyages()
    }

    fun deleteAvaloirsNotIn(ids: List<Int>) {
        return avaloirDao.deleteAvaloirsNotIn(ids)
    }

}