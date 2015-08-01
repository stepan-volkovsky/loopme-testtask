#!/usr/bin/python
import MySQLdb
from random import randint, choice

db = MySQLdb.connect("localhost","loopme","loopme","loopme")
cursor = db.cursor()

sql = "insert into Creatives(description, url, os, countries, excluded_os, excluded_countries) values ('{0}','http://google.com','{1}','{2}','{3}', '{4}')"

countries = ('AF', 'DZ', 'BY', 'KH', 'TD', 'CO', 'FO', 'ET', 'GR', 'HK', 'JO', 'IT', 'PL', 'QA', 'UA', 'US', 'UK')
os = ('android', 'ios', 'win', 'blackberry')

for index in range(2000):
	clist = []
	for cnum in range(randint(1,5)):
		cchoice = choice(countries)
		while(cchoice in clist):
			cchoice = choice(countries)
		clist.append(cchoice)
	
	oslist = []
	for osnum in range(randint(1,2)):
		oschoice = choice(os)
		while(oschoice in oslist):
			oschoice = choice(os)
		oslist.append(oschoice)

	exclist = []
	for excnum in range(randint(1,5)):
		excchoice = choice(countries)
		while(excchoice in exclist):
			excchoice = choice(countries)
		exclist.append(excchoice)
	
	exoslist = []
	for exosnum in range(randint(1,2)):
		exoschoice = choice(os)
		while(exoschoice in exoslist):
			exoschoice = choice(os)
		exoslist.append(exoschoice)
	
	strclist = ','.join(clist)
	stroslist = ','.join(oslist)
	strexclist = ','.join(exclist)
	strexoslist = ','.join(exoslist)
	desc = "ad for %s on %s excluding %s on %s" % (strclist, stroslist, strexclist, strexoslist)
	query = sql.format(desc, stroslist, strclist, strexoslist, strexclist)

	try:
		cursor.execute(query)
		db.commit()
	except Exception as e:
		print e
		db.rollback()

db.close
