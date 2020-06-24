import json

path = "/Users/luke/Documents/progressive_technicalities/src/main/resources/assets/progressivetechnicalities/models/item/"

while True:
	name = input("name? ")

	data = {
		"parent": "item/generated", 
		"textures": {
			"layer0": "progressivetechnicalities:items/" + name
		}
	}

	with open(path + name + ".json", "w") as f:
		f.write(json.dumps(data, indent=4))
