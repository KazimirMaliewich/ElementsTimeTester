# ElementsTimeTester Application

## Quick Description

The ElementsTimeTester is a console-app-based system developed using Java. It allows users to search, add, romove elements and test their speed on different amount.

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation Guide](#installation-guide)
- [Configuration](#configuration)
- [Running the Project](#running-the-project)
- [Directory Structure](#directory-structure)
- [Contribution Guidelines](#contribution-guidelines)
- [Branch Naming Conventions and Commit Message Guidelines](#branch-naming-conventions-and-commit-message-guidelines)

_Use [github-markdown-toc](https://github.com/ekalinin/github-markdown-toc) to automatically generate the Table of Contents._

---

## Prerequisites

Ensure the following tools and software are installed on your machine before setting up the project:

- [Java language](https://www.java.com/ru/download/) (v23.0.1) or higher
- [Java Development Kit](https://www.oracle.com/java/technologies/downloads/) (24.0.1) or higher
---

# Installation Guide

Follow these steps to set up the project locally:

1. Clone the repository:

  if use http 
Bash
```

   git clone https://github.com/KazimirMaliewich/ElementsTimeTester.git
   cd ElementsTimeTester
   ```
  if use ssh
Bash
```
    git clone git@github.com:KazimirMaliewich/ElementsTimeTester.git
    cd ElementsTimeTester
```

5. Start the app:

1. Open your project in any code editor you use which supports jdk (VS Code, IntelIJ etc.)
2. Open src directory and then right click Main.java file and click Run

# Testing

On your console you'll see text which will ask you for a choice. You can choose one option at a time by inserting numbers.

# Directory Structure

A brief explanation of the most important folders in the project:
```
ElementsTimeTester/
│
├── src/                   # Source code for the application
    ├── Main.java          # Main file with all classes included

```

## Branch Naming Convention:

- Use the following naming convention for branches:
  - feature/<short-description>: For new features
  - bugfix/<short-description>: For bug fixes
  - hotfix/<short-description>: For urgent fixes

Examples:

Bash
```
feature/add-user-authentication
bugfix/fix-login-issue
```