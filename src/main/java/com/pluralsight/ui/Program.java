package com.pluralsight.ui;

import com.pluralsight.data.DataManager;
import com.pluralsight.ui.UserInterface;

public class Program {
    private static final int ALL_CATEGORIES = -1;
    private static DataManager dataManager;
    private static UserInterface userInterface = new UserInterface();

    public static void run(DataManager dm) {
        dataManager = dm;

        do {
            userInterface.display();
        } while (true);
    }
}
