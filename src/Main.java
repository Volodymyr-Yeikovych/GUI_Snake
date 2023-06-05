import controller.ProgramController;
import dao.GameDao;
import view.SwingView;

public class Main {
    public static void main(String[] args) {
        ProgramController controller = new ProgramController(new SwingView(), new GameDao());
        controller.start();
    }
}