/*
Copyright © 2025 Harrison Jansen van Beek <harrison@jansenvanbeek.me>
*/
package cmd

import (
	"fmt"
	"log"
	"os"
	"sort"
	"strings"
	"github.com/spf13/cobra"
)
var dir *string
var shouldSort, reverse, all, files *bool

// lsCmd represents the ls command
var lsCmd = &cobra.Command{
	Use:   "ls",
	Short: "list all entries in directory",
	Long: `This function mimics the functionality of the unix ls command. **Add more detail here.`,
	Run: func(cmd *cobra.Command, args []string) {
		entries, err := os.ReadDir(*dir)
		if err != nil {
			log.Fatal(err)
		}
		if *reverse {
			sortFiles(&entries)
		}
		for _, entry := range entries {
			if !*all && strings.HasPrefix(entry.Name(), "."){
				continue
			}
			fmt.Println(entry.Name())

		}

	},
}

func init() {

	dir = lsCmd.PersistentFlags().StringP("directory", "d", ".", "set a directory to read all entries in")
	shouldSort = lsCmd.PersistentFlags().BoolP("sort", "s", true, "Sort output alphabetically in ascending order")
	reverse = lsCmd.PersistentFlags().BoolP("reverse", "r", false, "Sort output alphabetically in descending order")
	all = lsCmd.PersistentFlags().BoolP("all", "a", false, "show hidden files in output")
	files = lsCmd.PersistentFlags().BoolP("files", "f", true, "show only regular files in output")

	rootCmd.AddCommand(lsCmd)

	// Here you will define your flags and configuration settings.

	// Cobra supports Persistent Flags which will work for this command
	// and all subcommands, e.g.:
	// lsCmd.PersistentFlags().String("foo", "", "A help for foo")

	// Cobra supports local flags which will only run when this command
	// is called directly, e.g.:
	// lsCmd.Flags().BoolP("toggle", "t", false, "Help message for toggle")
}

func sortFiles(entries *[]os.DirEntry) {
	if entries == nil || len(*entries) == 0 {
        return
    }
    s := *entries
    sort.SliceStable(s, func(i, j int) bool {
            return s[i].Name() > s[j].Name()
    })
    *entries = s

}
