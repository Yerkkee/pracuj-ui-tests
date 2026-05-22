package com.yerke.pracuj.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.getDriver();
    }

    @After
    public void tearDown() {
    }

    @AfterAll
    public static void tearDownAll() {
        DriverManager.quitDriver();
    }
}