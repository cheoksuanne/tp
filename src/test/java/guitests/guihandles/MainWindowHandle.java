package guitests.guihandles;

import seedu.address.TestApp;

/**
 * Provides a handle for {@code MainWindow}.
 */
public class MainWindowHandle extends GuiHandle {

    private final PersonListPanelHandle personListPanel;
    private final ResultDisplayHandle resultDisplay;
    private final CommandBoxHandle commandBox;
    private final StatusBarFooterHandle statusBarFooter;
    private final MainMenuHandle mainMenu;
    private final BrowserPanelHandle browserPanel;

    public MainWindowHandle() {
        super(TestApp.APP_TITLE);

        personListPanel = new PersonListPanelHandle();
        resultDisplay = new ResultDisplayHandle();
        commandBox = new CommandBoxHandle(TestApp.APP_TITLE);
        statusBarFooter = new StatusBarFooterHandle();
        mainMenu = new MainMenuHandle();
        browserPanel = new BrowserPanelHandle();
    }

    public PersonListPanelHandle getPersonListPanel() {
        return personListPanel;
    }

    public ResultDisplayHandle getResultDisplay() {
        return resultDisplay;
    }

    public CommandBoxHandle getCommandBox() {
        return commandBox;
    }

    public StatusBarFooterHandle getStatusBarFooter() {
        return statusBarFooter;
    }

    public MainMenuHandle getMainMenu() {
        return mainMenu;
    }

    public BrowserPanelHandle getBrowserPanel() {
        return browserPanel;
    }
}