package pages.patient

import pages.ScaffoldPage

class OverviewPage extends ScaffoldPage {
    static url = "patient/overview"

    static at = {
		title == "Overblik - nye målinger"
    }

    static content = {
        messageLink { $("a", text: "Beskeder") }
        completedQuestionnariesLink { $("a", text: "Vis egne besvarede skemaer") }
    }
}
