import org.opentele.server.model.Schedule

testDataConfig {
    sampleData {
        'org.opentele.server.model.questionnaire.QuestionnaireHeader' {
            name = {->
                "TestQuestionnaireHeader${System.nanoTime()}"
            }
        }
        'org.opentele.server.model.QuestionnaireSchedule' {
            type = Schedule.ScheduleType.UNSCHEDULED
            internalTimesOfDay = ""
            internalWeekdays = ""
            internalDaysInMonth = ""
        }
        'org.opentele.server.model.questionnaire.StandardSchedule' {
            type = Schedule.ScheduleType.UNSCHEDULED
            internalTimesOfDay = ""
            internalWeekdays = ""
            internalDaysInMonth = ""
        }
        'org.opentele.server.model.User' {
            password: "foobar1234"
        }
    }
}
