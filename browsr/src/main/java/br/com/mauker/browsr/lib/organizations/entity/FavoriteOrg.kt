package br.com.mauker.browsr.lib.organizations.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteOrgs")
data class FavoriteOrg(
    @PrimaryKey(autoGenerate = false) val orgId: Int
)
