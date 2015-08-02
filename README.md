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
