package org.kathes.guava;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Guava的Objects方法
 * 
 * @author wyshenfei
 * 
 */
public class ObjectsTest {

	public static void main(String[] args) {
		System.out.println("Objects.equals()方法示例:");
		System.out.println(Objects.equal("a", "a"));
		System.out.println(Objects.equal(null, "a"));
		System.out.println(Objects.equal("a", null));
		System.out.println(Objects.equal(null, null));

		System.out.println("\nMoreObjects.toStringHelper()方法示例:");
		System.out.println(MoreObjects.toStringHelper("Person").add("name", "kathes").add("age", 27).add("test", null).toString());
		System.out.println(MoreObjects.toStringHelper("Person").add("name", "kathes").add("age", 27).add("test", null).omitNullValues().toString());

		System.out.println("\nComparisonChain示例");
		Book book1 = new Book("kathes", "guava入门", "google", "github", 0.00);
		Book book2 = new Book("kathes", "guava入门", "google", "github", 0.00);
		System.out.println(book1.hashCode());
		System.out.println(book1.toString());
		System.out.println(book2.hashCode());
		System.out.println(book2.toString());
		System.out.println(book1.equals(book2));
		System.out.println(book1 == book2);
		System.out.println(book1.compareTo(book2));

		Set<Book> bookSet = new HashSet<Book>();
		bookSet.add(book1);
		bookSet.add(book2);
		System.out.println(bookSet);

	}

	public static class Book implements Comparable<Book> {
		private String author;
		private String title;
		private String publisher;
		private String isbn;
		private double price;

		public Book(String author, String title, String publisher, String isbn, double price) {
			super();
			this.author = author;
			this.title = title;
			this.publisher = publisher;
			this.isbn = isbn;
			this.price = price;
		}

		@Override
		public int compareTo(Book o) {
			return ComparisonChain.start()
					// 链式比较,在第一个非0处返回
					.compare(this.title, o.getTitle()).compare(this.author, o.getAuthor()).compare(this.publisher, o.getPublisher()).compare(this.isbn, o.getIsbn())
					.compare(this.price, o.getPrice()).result();
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(title, author, publisher, isbn);// hashCode生成
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this) // toString
					.omitNullValues() // 忽略null属性
					.add("title", title).add("author", author).add("publisher", publisher).add("price", price).add("isbn", isbn).toString();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Book) {
				Book that = (Book) obj;
				return Objects.equal(author, that.author) && Objects.equal(title, that.title) && Objects.equal(publisher, that.publisher) && Objects.equal(isbn, that.isbn)
						&& Objects.equal(price, that.price);
			}
			return false;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

	}

}
