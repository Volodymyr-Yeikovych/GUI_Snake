package controller;

import dao.Dao;
import view.View;

import java.util.Arrays;
import java.util.EventListener;

public class ProgramController {

    private View view;
    private Dao dao;

    public ProgramController(View view, Dao dao) {
        this.view = view;
        this.dao = dao;
    }

    public static void notifyView() {

    }

    public void start() {
        dao.initBoard(27, 18);
        dao.initSnake();
        view.displayBoard(dao.getBoard());
        view.displaySnake(dao.getSnake());
        view.displayApple(dao.getApple());
    }
}
