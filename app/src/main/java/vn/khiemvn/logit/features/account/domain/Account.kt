package vn.khiemvn.logit.features.account.domain

enum class AccountType {
    CASH, BANK, WALLET, CREDIT, SAVING
}

data class Account(
    val id: String,
    val name: String,
    val balance: Double,
    val type: AccountType,
    val isExcludedFromSummary: Boolean
)
