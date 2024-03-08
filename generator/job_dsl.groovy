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
    shell('make fclean')
    shell('make')
    shell('make tests_run')
    shell('make clean')
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
