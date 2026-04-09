# MCEndgame

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.11-brightgreen)
![Mod Loader](https://img.shields.io/badge/Loader-Fabric-blue)
![License](https://img.shields.io/badge/License-CC0--1.0-lightgrey)
[![Modrinth](https://img.shields.io/badge/Modrinth-MCEndgame-green)](https://modrinth.com/project/mcendgame/)

**MCEndgame** is a Minecraft Fabric mod that enhances the endgame experience by introducing new layers of progression, challenging dungeons, powerful custom gear, and a deep itemization system.


---

## Features

- Procedurally generated **dungeons** with scaling difficulty
- **Boss encounters** with unique AI, attacks, and animations
- **Custom armor sets** with full 3D models powered by GeckoLib
- **Unique equipment** with special properties and custom attributes
- A deep **custom attribute system** with elemental damage, dodge, magic find, and much more
- **Totem slots** for passive build-defining bonuses
- A **Crystal Forge** to modify and improve your gear
- **Aspects** – dungeon defining 


## 🏰 Dungeons

Dungeons are procedurally generated pocket dimensions that you access through a **Dungeon Device** block. Each dungeon run scales with your **Dungeon Level**, increasing enemy difficulty and the quality of rewards.

- **Dungeon Levels** – Track your progression and unlock harder content
- **Room-based generation** – Rooms are assembled from templates
- **Encounter types** – Combat encounters with waves of enemies, totem encounters, and boss rooms
- **Dungeon Loot** – Equipment with randomized custom attributes
- **Dungeon Completion** – Defeating the final boss closes the dungeon and grants rewards

## 👾 Enemies & Bosses

Dungeons are populated with scaled versions of vanilla mobs and unique custom bosses, each with hand-crafted AI and GeckoLib animations.

### Bosses

| Boss | Description |
|---|---|
| **Arachne** | A giant spider with web-shot attacks and a hook ability |
| **Bonecrusher** | A powerful melee bruiser with slam and leap attacks |
| **Elf Duelist** | An agile fighter using teleport and melee attacks |

### Elite Mobs

Standard dungeon enemies can spawn as **Elite** variants with enhanced stats, custom equipment, and potion effects.

## 🗡️ Items

Custom Armor, Weapons, Tools

### Totems

Totems occupy dedicated **Totem Slots** (accessible via the Totem screen) and provide permanent passive bonuses while equipped.

### Crystals & the Crystal Forge

The **Crystal Forge** block allows you to use Crystals to modify your equipment.

| Crystal | Effect |
|---|---|
| **Calibration Crystal** | Re-rolls attribute values on an item |
| **Corruption Crystal** | Applies a Corruption effect with unpredictable outcomes |
| **Permutation Crystal** | Swaps attribute types on an item |
| **Reforge Crystal** | Completely re-rolls the attributes on an item |
| **Sacrifice Crystal** | Destroys an item to extract resources |

### Aspects

Aspects are special items that provide unique, playstyle-defining passive or active effects.


## 📊 Custom Attribute System

Equipment found in dungeons is imbued with **Custom Attributes** that go beyond vanilla Minecraft. Attributes are divided into offensive, defensive, and utility categories.

## 💫 Status Effects

## 🧱 Custom Blocks

## Commands

- **Item Filter** – Configure which dungeon item rarities and types are automatically picked up
- **Killer Screen** – Track kill counts and statistics

---

## Getting Started

### Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft **1.21.11**
2. Download [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download all [required dependencies](`#dependencies`)
4. Place all `.jar` files into your `.minecraft/mods` folder
5. Launch the game

### Dependencies

| Dependency | Version |
|---|---|
| [Fabric Loader](https://fabricmc.net/use/) | ≥ 0.18.4 |
| [Fabric API](https://modrinth.com/mod/fabric-api) | ~0.141.3+1.21.11 |
| [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin) | ~1.13.9+kotlin.2.3.10 |
| [Fantasy](https://github.com/NucleoidMC/fantasy) | ~0.7.0+1.21.11 |
| [GeckoLib](https://modrinth.com/mod/geckolib) | ~5.4.4 |

### Building from Source

TODO env variables (MAUCONFRAMEWORK_REPO_KEY -> normal github access key)
```bash
git clone https://github.com/maucon/MCEndgame-fabric.git
cd MCEndgame-fabric
./gradlew build
```
> Note: Building requires a valid GitHub token set as the `MAUCONFRAMEWORK_REPO_KEY` environment variable.

---

## Contributing

If you have a feature request or found a bug, please open an issue. If you'd like to contribute a fix or improvement, feel free to fork the repository and submit a pull request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact

Should you have any questions or encounter any difficulties, please don't hesitate to open an issue or join the `Discussions` section.

---

## Acknowledgments

* [FabricMC](https://fabricmc.net/)
* [Geckolib](https://modrinth.com/mod/geckolib/versions)
* [NucleoidMC/fantasy](https://github.com/NucleoidMC/fantasy)
* [Path of Exile](https://www.pathofexile.com/)

---

## License

This project is licensed under the CC0-1.0 License. See the [LICENSE](https://github.com/maucon/MCEndgame-fabric/blob/master/LICENSE) file for details.