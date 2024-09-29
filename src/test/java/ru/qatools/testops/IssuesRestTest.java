package ru.qatools.testops;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Layer("rest")
@Owner("mr.one")
@Feature("Issues")
public class IssuesRestTest {

    private static final String OWNER = "allure-framework";
    private static final String REPO = "allure2";

    private final RestSteps steps = new RestSteps();

    @TM4J("AE-T1")
    @Story("Создание нового issue")
    @Microservice("Billing")
    @Tags({@Tag("api"), @Tag("smoke")})
    @ParameterizedTest(name = "Создание через API")
    @ValueSource(strings = {"First Note", "Second Note"})
    @DisplayName("Создание нового issue.")
    public void shouldCreateIssue(@Param(value = "Title") String title) {
        steps.createIssueWithTitle(OWNER, REPO, title);
        steps.shouldSeeIssueWithTitle(OWNER, REPO, title);
    }

    @TM4J("AE-T2")
    @Story("Закрытие существующего issue")
    @Microservice("Repository")
    @Tags({@Tag("api"), @Tag("regress")})
    @JiraIssues({@JiraIssue("AE-1")})
    @ParameterizedTest(name = "Закрытие через API")
    @ValueSource(strings = {"First Note", "Second Note"})
    @DisplayName("Удаление существующего issue.")
    public void shouldDeleteIssue(@Param(value = "Title") String title) {
        steps.createIssueWithTitle(OWNER, REPO, title);
        steps.closeIssueWithTitle(OWNER, REPO, title);
    }

}
