package controllers.book.tabs;

import controllers.BaseTableController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksNotAvailableController extends BaseTableController<Book> implements Initializable {
    @FXML
    private TableView<Book> tvNotAvailableBooks;
    @FXML
    private TableColumn tcId, tcTitle, tcAuthor, tcEdition, tcYearOfPublication, tcStudentId, tcDateOfGive, tcDateOfTake;
    @FXML
    private Button btnTakeBook;
    @FXML
    private TextField tfSearch;

    private static BooksNotAvailableController instance;

    public static BooksNotAvailableController getInstance() {
        return instance;
    }

    @Override
    protected TableView<Book> getTableView() {
        return tvNotAvailableBooks;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return tfSearch;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(bookService.getNotAvailableBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tcEdition.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
        tcYearOfPublication.setCellValueFactory(new PropertyValueFactory<Book, String>("yearOfPublication"));
        tcStudentId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("studentId"));
        tcDateOfGive.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfGive"));
        tcDateOfTake.setCellValueFactory(new PropertyValueFactory<Book, String>("dateOfTake"));
        tvNotAvailableBooks.setItems(observableList);
        tvNotAvailableBooks.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initTableData();
        setButtonTakeBookListener();
        setTextFieldFindBookListener();
    }

    private void setButtonTakeBookListener() {
        btnTakeBook.setOnAction(event -> {
            if (getSelectionItem() != null) {
                bookService.takeBook(getSelectionItem());
                initTableData();
            }
        });
    }

    private void setTextFieldFindBookListener() {
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search();
            }
        });
    }
}