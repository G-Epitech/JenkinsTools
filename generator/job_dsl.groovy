job("Epitech/$REPO_NAME") {
  description("Generated job for $REPO_NAME project")

  wrappers {
    preBuildCleanup()
  }

  scm {
    git {
      remote {
        github("G-Epitech/$REPO_NAME", 'ssh')
        credentials('6e3e4aa7-d673-4f95-b2f7-f5407de27880')
      }
    }
  }

  triggers {
    githubPush()
  }

  steps {
    conditionalSteps {
      condition {
        stringsMatch("TYPE", 'Makefile', false)
      }

      steps {
        shell('docker run --rm -v "$PWD:/usr/app" epitechcontent/epitest-docker make fclean')
        shell('docker run --rm -v "$PWD:/usr/app" epitechcontent/epitest-docker make')
        shell('docker run --rm -v "$PWD:/usr/app" epitechcontent/epitest-docker make tests_run')
        shell('docker run --rm -v "$PWD:/usr/app" epitechcontent/epitest-docker make clean')
      }
    }
  }

  publishers {
    gitHubCommitStatusSetter {
      commitShaSource {
        buildDataRevisionShaSource()
      }
      contextSource {
        defaultCommitContextSource()
      }
      reposSource {
        anyDefinedRepositorySource()
      }
      statusBackrefSource {
        buildRefBackrefSource()
      }
      statusResultSource {
        defaultStatusResultSource()
      }
    }
  }
}
