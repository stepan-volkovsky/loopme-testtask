# loopme-testtask

To create database, run 
```
mysql -uroot < createdb.sql
```

To fill database with test data, run 
```
python fillthetable.py
```
note, that MySQLdb is needed for this to work. 
It can be downloaded here - http://sourceforge.net/projects/mysql-python
To install it, run
```
tar xzf MySQL-python-${version}.tar.gz
cd MySQL-python-${version}
python setup.py build
python setup.py install
```
Alternatively, for Fedora users, it can be installed using yum:
```
sudo yum install -y MySQL-python
```

To run the project, clone repository and execute
```
mvn exec:java
```
in project dir

## further improvements
First of all, searching by a substring in a text field is extremely slow.
For speeding this up, I suggest decomposing `Creatives` table and making dictionaries for countries and operating systems.

Done that, there are options at the top of my head:
1. Create 4 conjunction tables for countries and operation systems.
	That will enable using index in search, but each of those conjunction tables are going to have huge amount of entries (max number can be computed using binomial coefficient).
	Huge tables would decrease performance of joins. To avoid that, data can be divided by regions, like Europe, Asia etc.;
2. Another option could be using bitwise mask for countries and operation systems in `Creatives` table. Dictionaries can store mask values, from which actual mask is computed and then compared to value in `Creatives` table using bitwise AND operation;
3. Yet another option is to use search engine with DB server.

Another topic is getting random results from db. Used approach is pretty expensive because it fetches all results and only after that randomizes them and cuts to given limit. As an option to speed this up, amount of fetching data can be reduced, like selecting only ids with the first query, shuffling them and selecting random ones. Problem here is equalizing chances of picking each Creative.

