Coverage: 69%

# Project: Inventory Management System 


This is an inventory management system written in Java that manages a MySQL database. Users can perform basic CRUD functionality on database items in four different tables. The user is able to input customer, item and order information into the system using the CLI.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system. Ensure prerequisites below are met.

You can clone my repostitory so that you are able to run the application on your device.
copy the following comannd onto the gitbash cmd line:
```
git clone https://github.com/salim1704/ims cd ims

```

### Prerequisites

What things you need to install the software and how to install them

 * Java Version 11 or higher.
 * MySQL Version 8 or higher.
 * Git
 *  Maven


### Installing

Once repo is cloned you will have access to the 'ims' schema. This is kept in the folder src/main/resources/sql-shemas.sql. 
This will need to have been run so input commands successfully run on the Command line interface.
Changes may need to be made to db.properties to allow java to connect with MySQL succesfully.

You can execute the file through your command line, using the following command:

```
java -jar target/ims-0.0.1-jar-with-dependencies.jar
```
Once database is created and jar file is running, use the command line to input data into your database.

## Running the tests

To run test on your system, firstly clone the repository linked above. Once in Eclipse IDE, tests can be run by right clicking on the test file and selecting
Run as Junit Test.

### Unit Tests 

Unit testing is used to test the functionality of each method. These methods must pass the test in order for the application to run properly. 
Below is a test I ran on a constructer and tests on a few setters. 

```
@Test
	public void FirstTest() {
		Item item = new Item("PS5", 500, 3L);
		assertEquals("PS5", item.getName());
		assertEquals(500, item.getPrice(), 0.02);
		assertEquals(3, item.getStock(), 1);

	}
  @Test
	public void setIdTEST() {
		Item item = new Item(1L, "hat", 100, 3L);
		item.setId(2L);
		assertEquals(Long.valueOf(2), item.getId());

	}

	@Test
	public void setNameTEST() {
		Item item = new Item(1L, "ball", 100, 2L);
		item.setName("ball");
		assertEquals("ball", item.getName());

	}
  
```

### Integration Tests 

Intergration test were possible using Mockito. Through the use of mockito we are able to see how methods interact with other classes 
without the method itself being used. This allows us to imitate the application as if it were running. 
Below are a few examples of tests run on my ItemController class

```
	@Mock
	private Utils utils;

	@Mock
	private ItemDAO dao;

	@InjectMocks
	private ItemController controller;

	@Test
	public void testCreate() {
		final String name = "PS5";
		final double price = 500D;
		final Long stock = 3L;
		final Item created = new Item(name, price, stock);

		Mockito.when(utils.getString()).thenReturn(name);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(utils.getLong()).thenReturn(stock);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Gloves", 5.99D, 4L));

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Abdul Salim** 

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used 
