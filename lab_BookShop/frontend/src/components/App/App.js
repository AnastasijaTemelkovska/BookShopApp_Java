import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Navigate, Routes, Route} from 'react-router-dom'
import Authors from "../Authors/authors";
import Books from "../Books/BookList/books";
import BookShopService from "../../repository/bookshopRepository";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import Login from "../Login/login"
import BookEdit from "../Books/BookEdit/BookEdit";




class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            books: []
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                        <Route path={"/authors"} exact render={() =>
                            <Authors authors={this.state.authors}/>}/>
                        <Route path={"/books/add"} exact render={() =>
                            <BookAdd authors={this.state.authors}
                                     onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit manufacturers={this.state.manufacturers}
                                         onEditBook={this.editBook}
                                         product={this.state.selectedBook}/>}/>
                        <Route path={"/books"} exact render={() =>
                            <Books books={this.state.books}
                                      onDelete={this.deleteBook}
                                      onEdit={this.getBook}/>}/>
                            <Route path={"/"} exact render={() =>
                                <Books books={this.state.books}
                                       onDelete={this.deleteBook}
                                       onEdit={this.getBook}/>}/>
                            <Route path={"/login"} exact render={() => <Login onLogin={this.fetchData}/>}/>
                    </Routes>
                            <Navigate to={"/books"}/>
                    </div>
                </main>
            </Router>
        );
    }


    componentDidMount() {
        this.loadAuthors();
        this.loadBooks();
    }

    loadAuthors = () => {
        BookShopService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }
    loadBooks = () => {
        BookShopService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }
    deleteBook = (id) => {
        BookShopService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        BookShopService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        BookShopService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }
/*
    editBook = (id, name, category, author, availableCopies) => {
        BookShopService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }*/


}

export default App;
