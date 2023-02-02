package winter_camp_afterburner.usecases.ui.impl

import winter_camp_afterburner.usecases.UseCase
import java.time.LocalDate

/**
 * 各担当に指示を出し、値洗い機能を遂行します
 */
class MenuInteractor(menus: Collection<MenuStructure>, private val repository: Repository, private val inputReceiver: InputReceiver, private val presenter: IPresenter) : UseCase {
    private val menuMap = menus.associateBy { it.key }

    companion object {
        const val FINAL_CLOSING_KEY = "f"
    }

    override fun run() {
        while (true) {
            val finalClosed = try {
                repository.getFinalClosingDate() == LocalDate.now()
            } catch (e: Exception) {
                false
            }
            presenter.showSelectMenuMessage()
            menuMap.run { if (finalClosed) filterNot { it.key == FINAL_CLOSING_KEY } else this }.run {
                presenter.showMenus(values.toList())
                val inputKey = inputReceiver.getInput()
                get(inputKey)?.run { usecase.run() } ?: presenter.showTryAgainMessage()
            }
        }
    }
    interface Repository{
        fun getFinalClosingDate():LocalDate
    }

    interface InputReceiver {
        fun getInput(): String
    }

    interface IPresenter {
        fun showSelectMenuMessage()
        fun showMenus(structures: List<MenuStructure>)
        fun showTryAgainMessage()
    }

    class MenuStructure(val key: String, val message: String, val usecase: UseCase)

}