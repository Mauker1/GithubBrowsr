package br.com.mauker.browsr.lib.organizations.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Organization(
    @field:Json(name="login") val login: String,
    @field:Json(name="id") val id: Int,
    @field:Json(name="node_id") val nodeId: String,
    @field:Json(name="url") val url: String,
    @field:Json(name="repos_url") val reposUrl: String,
    @field:Json(name="events_url") val eventsUrl: String,
    @field:Json(name="hooks_url") val hooksUrl: String,
    @field:Json(name="issues_url") val issuesUrl: String,
    @field:Json(name="members_url") val membersUrl: String,
    @field:Json(name="public_members_url") val publicMembersUrl: String,
    @field:Json(name="avatar_url") val avatarUrl: String,
    @field:Json(name="description") val description: String? = null,
    var isFav: Boolean = false,
)
