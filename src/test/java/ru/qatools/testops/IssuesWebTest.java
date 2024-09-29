package ru.qatools.testops;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Layer("web")
@Owner("mr.two")
@Feature("Issues")
public class IssuesWebTest {

    private static final String OWNER = "allure-framework";
    private static final String REPO = "allure2";

    private static final String ISSUE_TITLE = "Some issue title here";

    private final WebSteps steps = new WebSteps();

    @BeforeEach
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @TM4J("AE-T3")
    @Microservice("Billing")
    @Story("Создание новой issue")
    @JiraIssues({@JiraIssue("AE-2")})
    @Tags({@Tag("web"), @Tag("critical")})
    @DisplayName("Создание issue для авторизованного пользователя")
    public void shouldCreateIssue() {
        steps.openIssuesPage(OWNER, REPO);
        steps.createIssueWithTitle(ISSUE_TITLE);
        steps.shouldSeeIssueWithTitle(ISSUE_TITLE);
    }

    @Test
    @TM4J("AE-T4")
    @Microservice("Repository")
    @Story("Создание новой issue")
    @Tags({@Tag("web"), @Tag("regress")})
    @JiraIssues({@JiraIssue("AE-1")})
    @DisplayName("Добавление заметки")
    public void shouldAddLabelToIssue() {
        steps.openIssuesPage(OWNER, REPO);
        steps.createIssueWithTitle(ISSUE_TITLE);
        steps.shouldSeeIssueWithTitle(ISSUE_TITLE);
    }

    @Test
    @TM4J("AE-T5")
    @Microservice("Repository")
    @Story("Закрытие существующей issue")
    @Tags({@Tag("web"), @Tag("regress")})
    @JiraIssues({@JiraIssue("AE-1")})
    @DisplayName("Закрытие issue для авторизованного пользователя")
    public void shouldCloseIssue() {
        steps.openIssuesPage(OWNER, REPO);
        steps.createIssueWithTitle(ISSUE_TITLE);
        steps.closeIssueWithTitle(ISSUE_TITLE);
        steps.shouldNotSeeIssueWithTitle(ISSUE_TITLE);
    }

    @AfterEach
    public void stopDriver() {
        steps.stopDriver();
    }

}