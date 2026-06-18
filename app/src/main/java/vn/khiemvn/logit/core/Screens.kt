package vn.khiemvn.logit.core

import kotlinx.serialization.Serializable

@Serializable
object HomeDestination

@Serializable
object AccountDestination

@Serializable
object CreateDestination

@Serializable
object ReportDestination

@Serializable
object SettingsDestination
@Serializable
data class TransactionDestination(val accountId: String)
