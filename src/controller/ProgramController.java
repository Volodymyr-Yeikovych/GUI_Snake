package controller;

import dao.Dao;
import view.View;

public class ProgramController {

    private View view;
    private Dao dao;
    public ProgramController(View view, Dao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void start() {
        view.displayBoard();
    }
}
