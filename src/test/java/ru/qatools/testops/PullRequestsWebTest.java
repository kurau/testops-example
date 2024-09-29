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
@Feature("Pull Requests")
public class PullRequestsWebTest {

    private static final String OWNER = "allure-framework";
    private static final String REPO = "allure2";

    private static final String BRANCH = "new-feature";

    private final WebSteps steps = new WebSteps();

    @BeforeEach
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @TM4J("AE-T6")
    @Microservice("Billing")
    @Story("Создание нового Pull Request")
    @Tags({@Tag("web"), @Tag("regress"), @Tag("smoke")})
    @JiraIssues({@JiraIssue("AE-1"), @JiraIssue("AE-2")})
    @DisplayName("Создание issue для авторизованного пользователя")
    public void shouldCreatePullRequest() {
        steps.openPullRequestsPage(OWNER, REPO);
        steps.createPullRequestFromBranch(BRANCH);
        steps.shouldSeePullRequestForBranch(BRANCH);
    }

    @Test
    @TM4J("AE-T7")
    @JiraIssue("AE-2")
    @Microservice("Repository")
    @Story("Закрытие существующего pull request")
    @Tags({@Tag("web"), @Tag("regress")})
    @DisplayName("Удаление issue для авторизованного пользователя")
    public void shouldClosePullRequest() {
        steps.openPullRequestsPage(OWNER, REPO);
        steps.createPullRequestFromBranch(BRANCH);
        steps.closePullRequestForBranch(BRANCH);
        steps.shouldNotSeePullRequestForBranch(BRANCH);
    }

    @AfterEach
    public void stopDriver() {
        steps.stopDriver();
    }

}