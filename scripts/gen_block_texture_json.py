import json

path = "/Users/luke/Desktop/Code/progressive_technicalities/src/main/resources/assets/progressivetechnicalities/"
path2 = "/Users/luke/Desktop/Code/progressive_technicalities/src/main/resources/data/progressivetechnicalities/loot_tables/blocks/"
while True:
	name = input("name? ")

	data = {
		"parent": "progressivetechnicalities:block/" + name
	}
	with open(path + "models/item/" + name + ".json", "w") as f:
		f.write(json.dumps(data, indent=4))

	data = {
		"parent": "block/cube_all",
		"textures": {
			"all": "progressivetechnicalities:blocks/" + name
		}
	}
	with open(path + "models/block/" + name + ".json", "w") as f:
		f.write(json.dumps(data, indent=4))

	data = {
		"variants": {
			"": {"model": "progressivetechnicalities:block/" + name
			}
		}
	}
	with open(path + "blockstates/" + name + ".json", "w") as f:
		f.write(json.dumps(data, indent=4))

	data = {
		"type": "minecraft:block",
		"pools": [
			{
			"rolls": 1.0,
			"entries": [
				{
				"type": "minecraft:item",
				"name": "progressivetechnicalities:" + name
				}
			]
			}
		]
	}
	with open(path2 + name + ".json", "w") as f:
		f.write(json.dumps(data, indent=4))
