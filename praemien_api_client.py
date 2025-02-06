import http.client
import json

host = 'localhost:8082'

path = '/praemien/api/anfrage'

anfrage = {
	'kilometerleistung': 10000,
	'fahrzeugtyp': 'PKW',
	'bundesland': 'Nordrhein-Westfalen',
	'kreis': 'Rhein-Sieg-Kreis',
	'stadt': 'Troisdorf',
	'postleitzahl': '53844',
	'bezirk': 'Kriegsdorf'
}
request_body = json.dumps(anfrage)
print('request: POST@http://' + host + path)
print('body', anfrage)


headers = {'Content-type': 'application/json'}

connection = http.client.HTTPConnection(host)
connection.request('POST', path, request_body, headers)
response = connection.getresponse().read().decode('utf-8')
connection.close()

print('response:', response)

