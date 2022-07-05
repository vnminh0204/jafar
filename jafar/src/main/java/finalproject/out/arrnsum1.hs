module Main where

import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 6) regF 
    , Jump (Abs 112) 
    , Nop 
    , Load (ImmValue 4) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regE 
    , Store regA (IndAddr regE) 
    , Jump (Abs 93) 
    , Nop 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Compute Add regB regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 1) regB 
    , Compute Add regF regB regB 
    , Compute Add regB regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Pop regB 
    , Pop regA 
    , Compute Mul regA regB regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regE 
    , Store regA (IndAddr regE) 
    , Load (ImmValue 4) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regE 
    , Store regA (IndAddr regE) 
    , Nop 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 12) 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regB 
    , Jump (Ind regB) 
    , Nop 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Pop regE 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regE regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regE regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regE regB 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regD 
    , Compute Add regF regD regD 
    , Load (ImmValue 2) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 1) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 0) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 6) regA 
    , Push regA 
    , Load (ImmValue 5) regD 
    , Compute Add regF regD regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 187) regA 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , EndProg 
    ]

main = runWithDebugger (debuggerSimplePrint myShow) [prog0]

