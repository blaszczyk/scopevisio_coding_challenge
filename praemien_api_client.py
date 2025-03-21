import http.client
import json

host = 'localhost:8082'

path = '/praemien/api/antrag'

antrag = {
	'kilometerleistung': 30000,
	'fahrzeugtyp': 'LKW',
	'ort': {
		'bundesland': 'Nordrhein-Westfalen',
		'kreis': 'Rhein-Sieg-Kreis',
		'stadt': 'Troisdorf',
		'postleitzahl': '53844',
		'bezirk': 'Kriegsdorf'
	}
}
request_body = json.dumps(antrag)
print('request: POST@http://' + host + path)
print('body', antrag)


headers = {'Content-type': 'application/json'}

connection = http.client.HTTPConnection(host)
connection.request('POST', path, request_body, headers)
response = connection.getresponse().read().decode('utf-8')
connection.close()

print('response:', response)

