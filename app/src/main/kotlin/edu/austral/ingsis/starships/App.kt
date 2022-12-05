package edu.austral.ingsis.starships

import edu.austral.ingsis.starships.ui.*
import edu.austral.ingsis.starships.ui.Collision
import edu.austral.ingsis.starships.ui.ElementColliderType.*
import javafx.application.Application
import javafx.application.Application.launch
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.stage.Stage
import starships.*
import kotlin.system.exitProcess

fun main() {
    launch(Starships::class.java)
}

class Starships() : Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val facade = ElementsViewFacade(imageResolver)
    private val keyTracker = KeyTracker()

    companion object {
        val SPACESHIP1 = ImageRef("spaceship1", 70.0, 70.0)
        val SPACESHIP2 = ImageRef("spaceship2", 70.0, 70.0)
        val SPACESHIP3 = ImageRef("spaceship3", 70.0, 70.0)
        val BULLET = ImageRef("bullet", 70.0, 70.0)
        val ASTEROID1 = ImageRef("asteroid", 70.0, 70.0)
        val ASTEROID2 = ImageRef("asteroid", 80.0, 80.0)
        val ASTEROID3 = ImageRef("asteroid", 90.0, 90.0)
        val game = Game()
    }

    override fun start(primaryStage: Stage) {
        val pane = mainGameScene()
        val menu = menuScene(primaryStage, pane)

        facade.timeListenable.addEventListener(TimeListener(facade.elements, game, this))
        facade.collisionsListenable.addEventListener(CollisionListener(game))
        keyTracker.keyPressedListenable.addEventListener(KeyPressedListener(game, this, primaryStage, pane, menu))

        keyTracker.scene = menu

        primaryStage.scene = menu
        primaryStage.height = 800.0
        primaryStage.width = 800.0

        facade.start()
        keyTracker.start()
        primaryStage.show()
    }

    override fun stop() {
        facade.stop()
        keyTracker.stop()
        exitProcess(0)
    }

    private fun mainGameScene(): StackPane {
        val pane = StackPane()
        val root = facade.view
        pane.children.addAll(root)
        root.id = "pane"
        return pane
    }

    private fun menuScene(primaryStage: Stage, pane: StackPane): Scene {
        val title = Label("Starships")
        title.textFill = javafx.scene.paint.Color.AQUAMARINE
        title.style = "-fx-font-family: VT323; -fx-font-size: 100;"

        val newGame = Label("New Game")
        newGame.textFill = javafx.scene.paint.Color.WHITE
        newGame.style = "-fx-font-family: VT323; -fx-font-size: 50"
        newGame.setOnMouseClicked {
            primaryStage.scene.root = pane
            game.start()
            createObjects()
        }

        newGame.setOnMouseEntered {
            newGame.cursor = Cursor.HAND
        }

        val newGameAux = HBox(70.0)
        newGameAux.alignment = Pos.CENTER
        newGameAux.children.addAll(newGame)

        val verticalLayout = VBox(50.0)
        verticalLayout.id = "menu"
        verticalLayout.alignment = Pos.CENTER
        verticalLayout.children.addAll(title, newGameAux)

        val menu = Scene(verticalLayout)
        menu.stylesheets.add(this::class.java.classLoader.getResource("styles.css")?.toString())
        return menu
    }

    fun pauseScene(primaryStage: Stage, pane: StackPane, menu: Scene): Scene {
        val resume = Label("Resume")
        resume.textFill = javafx.scene.paint.Color.WHITE
        resume.style = "-fx-font-family: VT323; -fx-font-size: 50"
        resume.setOnMouseClicked {
            primaryStage.scene = menu
            primaryStage.scene.root = pane
            game.isPaused = false
        }

        resume.setOnMouseEntered {
            resume.cursor = Cursor.HAND
        }

        val finishGame = Label("Finish game")
        finishGame.textFill = javafx.scene.paint.Color.WHITE
        finishGame.style = "-fx-font-family: VT323; -fx-font-size: 50;"
        finishGame.setOnMouseClicked {
            game.showResults()
            stop()
        }
        finishGame.setOnMouseEntered {
            finishGame.cursor = Cursor.HAND
        }

        val verticalLayout = VBox(50.0)
        verticalLayout.id = "pause"
        verticalLayout.alignment = Pos.CENTER
        verticalLayout.children.addAll(resume, finishGame)
        val pause = Scene(verticalLayout)
        pause.stylesheets.add(this::class.java.classLoader.getResource("styles.css")?.toString())
        return pause
    }

    private fun createObjects(){
        val objects = game.objects
        for(gameObject in objects){
            facade.elements[gameObject.id] = ElementModel(gameObject.id, gameObject.position.x, gameObject.position.y, gameObject.size.height, gameObject.size.width, gameObject.rotationDegrees, convertShape(gameObject.shape), convertImage(gameObject))
        }
    }

    fun convertShape(shape: ObjectShape) : ElementColliderType {
        return when (shape) {
            ObjectShape.CIRCULAR -> Elliptical
            ObjectShape.RECTANGULAR -> Rectangular
            ObjectShape.TRIANGULAR -> Triangular
        }
    }

    fun convertImage(gameObject: GameObject) : ImageRef? {
        return if (gameObject.style == ObjectStyle.SPACESHIP1) SPACESHIP1
        else if(gameObject.style == ObjectStyle.SPACESHIP2) SPACESHIP2
        else if (gameObject.style == ObjectStyle.SPACESHIP3) SPACESHIP3
        else if (gameObject.style == ObjectStyle.ASTEROID1) ASTEROID1
        else if (gameObject.style == ObjectStyle.ASTEROID2) ASTEROID2
        else if (gameObject.style == ObjectStyle.ASTEROID3) ASTEROID3
        else BULLET
    }
}

class TimeListener(private val elements: Map<String, ElementModel>, private val game: Game, private val starships: Starships) : EventListener<TimePassed> {
    override fun handle(event: TimePassed) {
        if (game.isOver) {
            game.showResults()
            game.reset()
            starships.stop()
        }
        game.update()
        val objects = game.objects ?: return;
        for (gameObject in objects) {
            val element = elements.get(gameObject.id)
            val values = gameObject.posRotSz
            if (element != null) {
                element.x.set(values[0])
                element.y.set(values[1])
                element.rotationInDegrees.set(values[2])
                element.height.set(values[3])
                element.width.set(values[4])
            }
        }
    }
}

class CollisionListener(private val game: Game) : EventListener<Collision> {
    override fun handle(event: Collision) {
        game.makeCollision(event.element1Id, event.element2Id)
    }

}

class KeyPressedListener(private val game: Game, private val starships: Starships, private val primaryStage: Stage, private val pane: StackPane, private val menu: Scene): EventListener<KeyPressed> {
    override fun handle(event: KeyPressed) {
        val map = game.configuration.keyboardConfiguration;
//        if (event.key == KeyCode.S && game.isPaused) game.saveGame()
        when(event.key) {
            map["forward-1"] -> game.moveShip(0, Vector(0.0, -1.0))
            map["backwards-1"] -> game.moveShip(0, Vector(0.0, 1.0))
            map["left-1"] -> game.moveShip(0, Vector(-1.0, 0.0))
            map["right-1"] -> game.moveShip(0, Vector(1.0, 0.0))
            map["stop-1"] -> game.moveShip(0, Vector(0.0, 0.0))
            map["rotate-left-1"] -> game.rotateShip(0, -5.0)
            map["rotate-right-1"] -> game.rotateShip(0, 5.0)
            map["shoot-1"] -> game.shoot(0)
            KeyCode.P -> {
                game.isPaused = true
                if (game.isPaused){
                    primaryStage.scene = starships.pauseScene(primaryStage, pane, menu)
                }
            }
            else -> {}
        }
        if (game.players.size == 2){
            when(event.key) {
                map["forward-2"] -> game.moveShip(1, Vector(0.0, -1.0))
                map["backwards-2"] -> game.moveShip(1, Vector(0.0, 1.0))
                map["left-2"] -> game.moveShip(1, Vector(-1.0, 0.0))
                map["right-2"] -> game.moveShip(1, Vector(1.0, 0.0))
                map["stop-2"] -> game.moveShip(1, Vector(0.0, 0.0))
                map["rotate-left-2"] -> game.rotateShip(1, -5.0)
                map["rotate-right-2"] -> game.rotateShip(1, 5.0)
                map["shoot-2"] -> game.shoot(1)
                KeyCode.P -> {
                    game.isPaused = true
                    if (game.isPaused){
                        primaryStage.scene = starships.pauseScene(primaryStage, pane, menu)
                    }
                }
                else -> {}
            }
        }
    }

}