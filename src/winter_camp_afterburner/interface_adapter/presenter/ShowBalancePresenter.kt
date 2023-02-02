package winter_camp_afterburner.interface_adapter.presenter

import winter_camp_afterburner.entities.issues.impl.Bond
import winter_camp_afterburner.entities.issues.impl.IssueType
import winter_camp_afterburner.entities.issues.Portfolio
import winter_camp_afterburner.entities.issues.impl.Balance
import winter_camp_afterburner.usecases.inventry_control.impl.ShowBalanceInteractor
import java.nio.charset.Charset

object ShowBalancePresenter : ShowBalanceInteractor.Presenter {
    override fun showNothingMessage() = println("残高ファイルは空です")

    override fun showPortfolio(portfolio: Portfolio<Balance>) {
        val d = '|'
        println(
            "${format("銘柄コード", 10)}$d${format("銘柄名", 16)}$d${format("償還年月日", 10)}$d${format(
                "利率",
                10
            )}$d${format("クーポン回数", 12)}$d${format("保有数量", 10)}$d${format("簿価", 10)}$d${format("時価", 10)}$d${format(
                "評価損益",
                16
            )}"
        )


        for (p in portfolio.values.filter { it.issue.issueType == IssueType.BOND }) {
            p.run {
                (issue as Bond).run {
                    System.out.printf("%-10s$d", code)
                    print(format(name, 16) + "|")
                    System.out.printf("%10d$d", maturity)
                    System.out.printf("%10.3f$d", coupon.toDouble())
                    System.out.printf("%12s$d", couponChance)
                }
                System.out.printf("%,10d$d", amount.toInt())
                System.out.printf("%,10d$d", bookValue.toInt())
                try {
                    System.out.printf("%,10d$d", marketValue!!.toInt())
                    System.out.printf("%,16d$d", profitAndLoss!!.toLong())
                } catch (e: Exception) {
                    System.out.printf("%-10s$d", "NA")
                    System.out.printf("%-16s$d", "NA")
                }
                println()
            }

        }
    }

    private fun format(target: String, length: Int): String {
        val byteDiff = (getByteLength(target, Charset.forName("utf-8")) - target.length) / 2
        return String.format("%-" + (length - byteDiff) + "s", target)
    }

    private fun getByteLength(string: String, charset: Charset): Int {
        return string.toByteArray(charset).size
    }
}