<!-- .github/copilot-instructions.md -->
# Copilot / AI Agent Instructions for this repository

This repository is a small Java demonstration of the Template Method design pattern. The guidance below focuses on the concrete, discoverable patterns, build/run steps, and safe ways an AI code assistant should change the code.

**Project Overview**
- **Main file:** `TemplateMethodDemo.java` — a single-file demo that contains an abstract `Character` class and two concrete subclasses (`Warrior`, `Mage`) plus a `main` that runs the demo.
- **Pattern illustrated:** Template Method. The key method is `Character.performBattle(...)` (declared `final`) which defines the fixed battle flow. Subclasses implement `prepare()` and `attack()` and may optionally override hook methods `beforeAttack()` and `afterAttack()`.

**What to preserve / why**
- Do not change the `final` template method `performBattle` except for bug fixes. It encodes the canonical control-flow for the example.
- Preserve the public surface used by the demo: `Character` constructor, `takeDamage(int)`, `isAlive()`, `getName()`, and the `main` entrypoint `TemplateMethodDemo.main`.
- The program output (strings printed to System.out) is part of the demo. Avoid altering text used for instructional output unless updating the demo for a user-requested change.

**Code patterns & conventions (specific to this repo)**
- Visibility: `prepare()` and `attack()` are `protected abstract`; hooks are `protected` with empty defaults. Follow that when adding subclasses.
- State: `health` is private on `Character` and mutated only by `takeDamage(int)`; prefer using that method rather than manipulating health directly.
- No build system: this is a plain Java source file (no `package`, no Maven/Gradle). Keep edits consistent with single-file compilation semantics.

**How to add a new role (example)**
- Create a new class in `TemplateMethodDemo.java` (same file) such as:

```java
class Archer extends Character {
    public Archer(String name, int health) { super(name, health); }
    @Override protected void prepare() { System.out.println("Archer readies arrow..."); }
    @Override protected void attack(Character opponent) { System.out.println("Archer fires!"); opponent.takeDamage(15); }
}
```

Then instantiate and call `performBattle(...)` from `main` following the existing demo style.

**Build / Run (Windows PowerShell)**
- Compile:
```
javac TemplateMethodDemo.java
```
- Run:
```
java TemplateMethodDemo
```

**Testing & debugging notes**
- There are no unit tests or build scripts in the repository. For quick checks, run the compiled class as shown above.
- When debugging logic changes to battle flow, add small `System.out.println(...)` traces near `performBattle` steps — but avoid removing the existing descriptive lines used for the demo.

**Safe edits for AI agents**
- Add new subclasses, small helper methods, or local refactors that keep the `performBattle` contract intact.
- When changing visibility or signatures of existing methods, update all uses in the single file (this repo has no other Java files).
- If you add new files, keep top-level `package` declaration empty unless you also add a simple build script (Maven/Gradle).

**Files of interest**
- `TemplateMethodDemo.java` — source of truth for behavior and examples.

If anything important is missing from these instructions or you want examples targeting tests or a build system (Maven/Gradle), tell me which direction you prefer and I will update this file.
