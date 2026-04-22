# ⭐ StarsGallery

Une application Android affichant une galerie de stars de cinéma avec notation, recherche et partage.

---

https://github.com/user-attachments/assets/e05df2b7-80ca-42f1-9e3d-2c584bff55ab
---
## 📱 Aperçu

| Splash Screen | Liste des Stars | Recherche | Modifier la note |
|---|---|---|---|
| Animation logo | RecyclerView | SearchView | AlertDialog |

---

## 🚀 Fonctionnalités

- **Splash Screen animé** — rotation, zoom, translation et fondu au lancement
- **Liste des stars** — affichage avec photo circulaire, nom et note (étoiles)
- **Recherche dynamique** — filtrage en temps réel via la SearchView
- **Modifier la note** — popup au clic sur une star pour changer son rating
- **Partage** — partage de l'app via les applications installées (WhatsApp, Gmail...)
- **Thème rouge bordeaux** — interface cohérente avec toolbar colorée

---

## 🏗️ Architecture du projet

```
com.example.starsgallery/
│
├── beans/
│   └── Star.java              # Modèle de données
│
├── dao/
│   └── IDao.java              # Interface CRUD générique
│
├── service/
│   └── StarService.java       # Singleton — logique métier
│
├── adapter/
│   └── StarAdapter.java       # RecyclerView + Filtre + Popup
│
└── ui/
    ├── SplashActivity.java    # Écran de démarrage animé
    └── ListActivity.java      # Activité principale
```

---

## 🛠️ Technologies utilisées

| Technologie | Version | Usage |
|---|---|---|
| Java | 11 | Langage principal |
| Android SDK | 36 | Plateforme cible |
| RecyclerView | 1.3.1 | Liste des stars |
| Glide | 4.15.1 | Chargement des images |
| CircleImageView | 3.1.0 | Photos circulaires |
| SearchView | AndroidX | Recherche |
| ShareCompat | AndroidX | Partage |

---

## ⚙️ Installation

### Prérequis
- Android Studio (2024+)
- JDK 11+
- Android SDK 24+
- Émulateur ou appareil Android

### Étapes

**1. Cloner ou ouvrir le projet**
```bash
git clone https://github.com/yourname/StarsGallery.git
```
ou ouvrir le dossier dans Android Studio.

**2. Ajouter les dépendances dans `build.gradle (app)`**
```gradle
implementation("androidx.recyclerview:recyclerview:1.3.1")
implementation("de.hdodenhof:circleimageview:3.1.0")
implementation("com.github.bumptech.glide:glide:4.15.1")
annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
```

**3. Ajouter la permission Internet dans `AndroidManifest.xml`**
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

**4. Ajouter les images des stars**

Placer les images dans `res/drawable/` :
```
res/drawable/
├── kate.jpg
├── george.jpg
├── michelle.jpg
└── scarlett.jpg
```

**5. Lancer l'app**
```
Run ▶ (Shift + F10)
```

---

## 📂 Fichiers clés

### `beans/Star.java`
Modèle représentant une star avec `id`, `name`, `img`, `star` (rating).

### `dao/IDao.java`
Interface générique définissant les méthodes CRUD : `create`, `update`, `delete`, `findById`, `findAll`.

### `service/StarService.java`
- Pattern **Singleton**
- Implémente `IDao<Star>`
- Méthode `seed()` pour initialiser les données

### `adapter/StarAdapter.java`
- Implémente `Filterable` pour la recherche
- Gère le **popup** de modification de note
- Utilise **Glide** pour le chargement des images

### `ui/SplashActivity.java`
- Animation : rotation + zoom + translation + fondu
- Redirige vers `ListActivity` après 5 secondes

### `ui/ListActivity.java`
- Initialise le `RecyclerView` avec `LinearLayoutManager`
- Gère la `SearchView` via `onCreateOptionsMenu()`
- Gère le partage via `ShareCompat.IntentBuilder`

---

## 🎨 Thème

```xml
<style name="Theme.StarsGallery" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="colorPrimary">#C62828</item>      <!-- Rouge bordeaux -->
    <item name="colorPrimaryDark">#8E0000</item>  <!-- Rouge foncé -->
    <item name="colorAccent">#FF5252</item>        <!-- Accent rouge -->
</style>
```

---

## 📋 Manifest

```xml
<uses-permission android:name="android.permission.INTERNET" />

<application android:theme="@style/Theme.StarsGallery">
    <activity android:name=".ui.SplashActivity" android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
    <activity android:name=".ui.ListActivity" />
</application>
```

---

## 🔍 Fonctionnement du filtre

```
Utilisateur tape "G"
        ↓
onQueryTextChange("G")
        ↓
starAdapter.getFilter().filter("G")
        ↓
performFiltering() → parcourt la liste originale `stars`
        ↓
publishResults() → met à jour `starsFilter` + notifyDataSetChanged()
        ↓
RecyclerView affiche uniquement "George Clooney"
```

---

## 💡 Pattern utilisé

```
View (Activity/Adapter)
        ↕
Service (StarService)
        ↕
DAO (IDao<Star>)
        ↕
Model (Star)
```

Architecture **MVC** avec séparation claire des couches.

---

## 👨‍💻 Auteur

Projet réalisé dans le cadre d'un cours de développement Android mobile.

---

## 📄 Licence

Ce projet est à des fins éducatives uniquement.


https://github.com/user-attachments/assets/eef19300-e376-411f-8dba-f7788f90796c



Uploading StarsGallery – StarAdapter.java [StarsGallery.app.main] 2026-04-22 20-50-10.mp4…

