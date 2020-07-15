import json, os

pieces = ["helmet", "chestplate", "leggings", "boots"]
patterns = [
	[
		"iii",
		"i i"
	],
	[
		"i i",
		"iii",
		"iii"
	],
	[
		"iii",
		"i i",
		"i i"
	],
	[
		"i i",
		"i i"
	]
]
basepath = "/Users/luke/Desktop/Code/progressive_technicalities/src/main/resources/data/progressivetechnicalities/recipes/armor/"

while True:
	name = input("armor name? (ie iron) ")
	material = input("material name? (ie iron_ingot) ")

	os.mkdir(basepath + name)
	path = basepath + name + "/"

	for i in range(4):
		piece_name = "{}_{}".format(name, pieces[i])
		data = {
			"type": "minecraft:crafting_shaped",
			"pattern": patterns[i],
			"key": {
				"i": {
				"item": "progressivetechnicalities:" + material
				}
			},
			"result": {
				"item": "progressivetechnicalities:" + piece_name
			}
		}

		with open(path + piece_name + ".json", "w") as f:
			f.write(json.dumps(data, indent=4))
