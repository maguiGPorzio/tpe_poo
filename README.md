# TPE Final 2024 - Programación Orientada a Objetos

🎨 **Resumen**  
Este proyecto es el Trabajo Final para Programación Orientada a Objetos en diciembre 2024. Desarrollamos una aplicación tipo "Paint" con diversas funcionalidades avanzadas:

- Herramienta de creación de rectángulos, cuadrados, círculos y elipses con diversos formatos.
- Transformaciones:
    - Dividir y duplicar figuras.
    - Voltear horizontal y verticalmente.
    - Traer al frente, enviar al fondo.
    - Copiar formato.
- Efectos visuales:
    - Sombra.
    - Gradiente.
    - Biselado.
- Manejo de capas:
  - Mostrar y ocultar.
  - Agregar.
  - Eliminar.

## 📂 Estructura del proyecto

El proyecto está dividido en dos capas principales: **backend** y **frontend**.

### 🖥️ Backend
Contiene las interfaces y modelos necesarios para manejar la lógica de negocio del proyecto.

#### **Interfaces**:
- `Choosable`
- `Divisible`
- `Duplicable`
- `Flippable`
- `Movable`
- `Rotable`

#### **Modelos**:
- `Circle`
- `Ellipse`
- `Figure`
- `Pair`
- `Point`
- `Rectangle`
- `Square`


- `CanvasState`
- `Layer`

### 🎨 Frontend
Encargado de gestionar la interfaz gráfica y la interacción con el usuario. Está organizado en módulos.

#### **Botones**:
- `CircleButton`
- `EllipseButton`
- `FigureButton`
- `RectangleButton`
- `SquareButton`

#### **Formatted**:
- `DrawableFigure`
- `Format`
- `FormattedCircle`
- `FormattedEllipse`
- `FormattedFigure`
- `FormattedFigureOval`
- `FormattedFigureRectangle`
- `FormattedRectangle`
- `FormattedSquare`


- `AppLauncher`
- `AppMenuBar`
- `CustomHBox`
- `CustomVBoxLeft`
- `CustomVBoxRight`
- `MainFrame`
- `PaintPane`
- `ShadowType`
- `StatusPane`